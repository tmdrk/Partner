function getJsonUrl(){
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
	//获取带"/"的项目名，如：/uimcardprj
	var _projectName="";
	if(curWwwPath.indexOf(".com")<0){	//非域名方式
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname;
		_projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	}
	//json路径 
	var area_json_url = _projectName+"/js/area/area.json";
	
	return area_json_url;
}
//省市县三层combobox初始化数据
function initArea(province,city,county){

	if(province==null || province==""){
		$.messager.alert('警告','province不能为空');
		return;
	}
	
	//一层Combo  
	$("#"+province).combobox({  
		 url:''+getJsonUrl(),
		 method:'get',
		 valueField:'itemCode',
		 textField:'itemName',
		 onSelect: function(rec){
			 if(rec!=null){
				 var url = rec.childList;
				 if(county!=null && county!=""){
					 $("#"+county).combobox('clear');
					 $("#"+county).combobox('loadData',[]);
					 $("#"+county).combobox('setText','-请选择-');
				 }
				 
				 if(city!=null && city!=""){
					 $("#"+city).combobox('clear');
					 $("#"+city).combobox('loadData',url);
					 $("#"+city).combobox('setText','-请选择-');
				 }
			 }
	     },
	     onLoadSuccess:function(){
	    	 $("#"+province).combobox('setText','-请选择-');
	     }
	});
	
	if(city==null || city==""){	//city为空，程序中断
		return;
	}
	
	//二层Combo
	$("#"+city).combobox({
		valueField:'itemCode',
		textField:'itemName',
		onSelect:function(rec){
			if(rec!=null){
				var url = rec.childList;
				if(county!=null && county!=""){
					$("#"+county).combobox('clear');
					$("#"+county).combobox('loadData',url);
					$("#"+county).combobox('setText','-请选择-');
				}
			}
		}
	});
	
	$("#"+city).combobox('setText','-请选择-');
	if(county==null || county==""){	//county为空，程序中断
		return;
	}
	
	//三层Combo
	$("#"+county).combobox({
		valueField:'itemCode',
		textField:'itemName'
	});
	
	
	$("#"+county).combobox('setText','-请选择-');
}


/**
 *	默认选中
 *	@param: provinceId:默认省标号
 *			cityId:默认市编号
 *			county:默认县编号 
 */
function selectArea(province,city,county,provinceId,cityId,countyId){
	$.getJSON(''+getJsonUrl(), function(data){
		$(data).each(function(){
			//获取默认选择的省id
			if(this.itemCode==provinceId){
				$("#"+province).combobox('select',provinceId);
				
				//市级json数据
				var cityList = this.childList;
				if(cityId!=null && cityId!=""){
					$("#"+city).combobox('loadData',cityList);
					$("#"+city).combobox('select',cityId);
					if(countyId!=null && countyId!=""){
						$("#"+county).combobox('select',countyId);
					}
				}
			}
		});
	});
}

function areaCity(city, provinceId, cityId){
	$.getJSON(''+getJsonUrl(), function(data){
		$(data).each(function(i){
			//获取默认选择的省id
			if(this.itemCode==provinceId){
				var cityList = this.childList;
				$(cityList).each(function(){
					if(this.itemCode == cityId) {
						$("#"+city).val(this.itemName);
					}
				});
			}
		});
	});
}
