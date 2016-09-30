/**
 * shenweiwei
 */
var folUtil={};

folUtil.getInputByName=function(name){
	return $('div input[name="'+name+'"]');
};

folUtil.isNull=function(data){
	if(data===null||data===undefined||data===''){
		return true;
	}
	return false;
};

folUtil.getComboBoxDataById=function(id){
	return $('#'+id).combobox('getValue');
};

folUtil.getComboBoxDatasById=function(id){
	return $('#'+id).combobox('getValues');
};

folUtil.setComboBoxYearDataById=function(id){
	var yearData=new Array();
	for(var i=1955;i<=2055;i++){
		yearData.push({display:i,value:i});
	}
	
	$('#'+id).combobox('loadData',yearData);
	var date=new Date();
	var yearnow=parseInt(date.getFullYear())-1955;
	$('#'+id).combobox('select',yearData[yearnow]['value']);
	
};

folUtil.setComboBoxMonthDataById=function(id){
	var monthData=new Array();
	for(var i=1;i<=12;i++){
		monthData.push({display:i,value:i});
	}
	
	$('#'+id).combobox('loadData',monthData);
	
	var date=new Date();
	var monthnow=parseInt(date.getMonth());
	$('#'+id).combobox('select',monthData[monthnow]['value']);
};
//
//folUtil.dataGridFormatterForButton=function(val,row){
//	return '<div class="btn btn-default btn-mini" ng-click="update()">修改</a>';
//};

folUtil.setComboBoxYesAndNoById=function(id){
	var data=[{display:'有',value:'1'},{display:'无',value:'0'}];
	$('#'+id).combobox('loadData',data);
};
//转换月份到2位
folUtil.convertMonthToTwoPoint=function(val){
	if(val.length<2){
		return "0"+val;
	}
	return val;
};

//根据NAME读取COOKIE
folUtil.getCookieByName=function(name){
	var strlist=document.cookie.split("; ");
	for(var i=0;i<strlist.length;i++){
		var temp=strlist[i].split("=");
		if(temp[0]===name){
			return temp[1];
		}
	}
};
folUtil.getMyformatter=function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	return y+'-'+(m<10?('0'+m):m);
};
folUtil.getMyparser=function(s){

	if(!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	if(!isNaN(y)&& !isNaN(m)){
		return new Date(y,m-1);
	}else {
		return new Date();
	}
};
folUtil.getTextBox=function(){
	$.extend($.fn.datagrid.methods, {
			editCell:function(jq,param){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field){
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
	            var ed = $(this).datagrid('getEditor', param);
	            if (ed){
	                if ($(ed.target).hasClass('textbox-f')){
	                    $(ed.target).textbox('textbox').focus();
	                } else {
	                    $(ed.target).focus();
	                }
	            }
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		},
	   enableCellEditing:function(jq){
	        return jq.each(function(){
	            var dg = $(this);
	            var opts = dg.datagrid('options');
	            opts.oldOnClickCell = opts.onClickCell;
	            opts.onClickCell = function(index, field){
	                if (opts.editIndex != undefined){
	                    if (dg.datagrid('validateRow', opts.editIndex)){
	                        dg.datagrid('endEdit', opts.editIndex);
	                        opts.editIndex = undefined;
	                    } else {
	                        return;
	                    }
	                }
	                dg.datagrid('selectRow', index).datagrid('editCell', {
	                    index: index,
	                    field: field
	                });
	                opts.editIndex = index;
	                opts.oldOnClickCell.call(this, index, field);
	            }
	        });
	    }
	});
}

//超时函数
//folUtil.logonTimeOut=function(interval,window){
//	console.log(localStorage.getItem('usedtime'));
//
//	if(folUtil.isNull(localStorage.getItem('surplustime'))){
//		localStorage.setItem('surplustime',SESSION_TIMEOUT-localStorage.getItem('usedtime'));
//	}else{
//		localStorage.setItem('surplustime',parseInt(localStorage.getItem('surplustime'))-parseInt(localStorage.getItem('usedtime')));
//	}
//	
//	if(localStorage.getItem('surplustime')>0&&!folUtil.isNull(localStorage.getItem('usedtime'))){
//		localStorage.setItem('surplustime',parseInt(localStorage.getItem('surplustime'))+parseInt(localStorage.getItem('usedtime')));
//	}
//	
//	var timer = interval(function(){
//		window.clearInterval(timer);
//		
//	},localStorage.getItem('surplustime'),1);
//		
//	localStorage.setItem('usedtime',0)
//	
//	timer.then(function(){
//		if(parseInt(localStorage.getItem('intervalActionNum'))<=0){
//			alert("用户登陆已过期，请重新登陆");
//			console.log("自动计划任务执行完毕");
//			window.location.href=FOL_SERVER_URL;
//			localStorage.removeItem('surplustime');
//			localStorage.removeItem('usedtime');
//			localStorage.setItem('intervalActionNum',parseInt(localStorage.getItem('intervalActionNum'))+1);
//		}
//	}, function(){
//		console.log("自动计划任务执行失败或重置");
//	});
//}
