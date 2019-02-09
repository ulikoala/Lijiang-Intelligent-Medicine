$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'api/salerecord/list',
        datatype: "json",
        colModel: [			
			{ label: 'orderno', name: 'orderno', index: 'orderNo', width: 50, key: true },
			{ label: '订单状态', name: 'orderstate', index: 'orderState', width: 80 }, 			
			{ label: '出药时间', name: 'outdrugtime', index: 'outDrugTime', width: 80 }, 			
			{ label: '付款方式', name: 'paytype', index: 'payType', width: 80 }, 			
			{ label: '应出药数量', name: 'shouldoutcount', index: 'shouldOutCount', width: 80 }, 			
			{ label: '实际出药数量', name: 'realoutcount', index: 'realOutCount', width: 80 }, 			
			{ label: '药品单价', name: 'outdrugprice', index: 'outDrugPrice', width: 80 }, 			
			{ label: '出药总价', name: 'outdrugallprice', index: 'outDrugAllPrice', width: 80 }, 			
			{ label: '药品名称', name: 'drugname', index: 'drugName', width: 80 }, 			
			{ label: '药品批号', name: 'drugbatch', index: 'drugBatch', width: 80 }, 			
			{ label: '药品唯一id', name: 'drugno', index: 'drugNo', width: 80 }, 			
			{ label: '批号唯一id', name: 'drugbatchno', index: 'drugBatchNo', width: 80 }, 			
			{ label: '出药状态', name: 'outdrugresult', index: 'outDrugResult', width: 80 }, 			
			{ label: '售药机简称', name: 'machineshortname', index: 'machineShortName', width: 80 }, 			
			{ label: '客户名称', name: 'clientname', index: 'clientName', width: 80 }, 			
			{ label: '货道号', name: 'positioncode', index: 'positionCode', width: 80 }			
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
		salerecord: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.salerecord = {};
		},
		update: function (event) {
			var orderno = getSelectedRow();
			if(orderno == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderno)
		},
		saveOrUpdate: function (event) {
			var url = vm.salerecord.orderno == null ? "api/salerecord/save" : "api/salerecord/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.salerecord),
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
			var ordernos = getSelectedRows();
			if(ordernos == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "api/salerecord/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ordernos),
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
		getInfo: function(orderno){
			$.get(baseURL + "api/salerecord/info/"+orderno, function(r){
                vm.salerecord = r.salerecord;
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