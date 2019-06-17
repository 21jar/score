$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'program/list',
        datatype: "json",
        colModel: [			
			{ label: '名称', name: 'name', index: 'name', width: 80 },
			{ label: '机构名称', name: 'deptName', index: 'dept_name', width: 80 },
			{ label: '总得分', name: 'score', index: 'score', width: 80 }
		],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            name: null
        },
		showList: true,
		title: null,
		program: {},
		judgesList: []
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.program = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.program.id == null ? "program/save" : "program/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.program),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "program/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "program/info/"+id, function(r){
                vm.program = r.program;
                vm.judgesList = r.judgesList;
				// 动态生成评委
				for(var i=0; i<vm.judgesList.length; i++) {
					var judges = vm.judgesList[i];
					var htmlStr="<div class=\"form-group\">\n" +
						"    <div class=\"col-sm-2 control-label\">" + judges.name + "</div>\n" +
						"    <div class=\"col-sm-10\">\n" +
						"        <input type=\"text\" class=\"form-control\" v-model=\"program.grade1\" onblur='grade("+judges.id+")'/>\n" +
						"    </div>\n" +
						"</div>"
					$("#score").after(htmlStr);
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
        grade: function (event) {
            var url = "program/grade";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.program),
                success: function(r){
                    if(r.code === 0){
                        // alert('操作成功', function(index){
                        //     vm.reload();
                        // });
						$("#message").text("操作成功")
                    }else{
                        alert(r.msg);
                    }
                }
            });
		}
	}
});

function grade(id) {
	var url = "program/grade";
	$.ajax({
		type: "POST",
		url: baseURL + url,
		contentType: "application/json",
		data: JSON.stringify(vm.program),
		success: function(r){
			if(r.code === 0){
				// alert('操作成功', function(index){
				// 	vm.reload();
				// });
				$("#message").text("操作成功")
			}else{
				alert(r.msg);
			}
		}
	});
}