$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'api/macpos/list',
        datatype: "json",
        colModel: [			
			{ label: 'machineno', name: 'machineno', index: 'machineNo', width: 50, key: true },
			{ label: '药品批号No', name: 'drugbatchno', index: 'drugBatchNo', width: 80 }, 			
			{ label: '此货道最大数量', name: 'maxnumber', index: 'maxNumber', width: 80 }, 			
			{ label: '此货道当前数量', name: 'nownumber', index: 'nowNumber', width: 80 }, 			
			{ label: '货道号', name: 'positioncode', index: 'positionCode', width: 80 }, 			
			{ label: '此货道状态', name: 'positionstate', index: 'positionState', width: 80 }, 			
			{ label: '机器简称', name: 'machineshortname', index: 'machineShortName', width: 80 }			
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
		showList: true,
		title: null,
		macpos: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.macpos = {};
		},
		update: function (event) {
			var machineno = getSelectedRow();
			if(machineno == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(machineno)
		},
		saveOrUpdate: function (event) {
			var url = vm.macpos.machineno == null ? "api/macpos/save" : "api/macpos/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.macpos),
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
			var machinenos = getSelectedRows();
			if(machinenos == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "api/macpos/delete",
                    contentType: "application/json",
				    data: JSON.stringify(machinenos),
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
		getInfo: function(machineno){
			$.get(baseURL + "api/macpos/info/"+machineno, function(r){
                vm.macpos = r.macpos;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});