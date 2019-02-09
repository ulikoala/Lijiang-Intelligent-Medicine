$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'api/mac/list',
        datatype: "json",
        colModel: [			
			{ label: 'machineno', name: 'machineno', index: 'machineNo', width: 50, key: true },
			{ label: '机器简称', name: 'machineshortname', index: 'machineShortName', width: 80 }, 			
			{ label: '机器地址', name: 'machineadress', index: 'machineAdress', width: 80 }, 			
			{ label: '设备经度', name: 'machinelongitude', index: 'machineLongitude', width: 80 }, 			
			{ label: '设备纬度', name: 'machinelatitude', index: 'machineLatitude', width: 80 }, 			
			{ label: '设备状态', name: 'machinestatus', index: 'machineStatus', width: 80 }			
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
		mac: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.mac = {};
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
			var url = vm.mac.machineno == null ? "api/mac/save" : "api/mac/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.mac),
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
				    url: baseURL + "api/mac/delete",
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
			$.get(baseURL + "api/mac/info/"+machineno, function(r){
                vm.mac = r.mac;
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