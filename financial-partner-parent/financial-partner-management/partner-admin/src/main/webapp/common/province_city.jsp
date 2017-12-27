<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(function(){	
        $("#province").combobox({
			url:'${app}/city_findAll.shtml',
			valueField:'item_code',
			textField:'item_name',
			onSelect:function(record){
				$("#city").combobox('clear');
				findCityByProvince(record.item_code);
				$("#city").combobox('setValue','请选择');
			}
		});
         
        $('#city').combobox({ 
             valueField:'item_code',
             textField:'item_name' 
        });
        
        $("#province").combobox('setValue','请选择');
        $("#city").combobox('setValue','请选择');
    });
 		
	//根据省查询市
	function findCityByProvince(value){
		$("#city").combobox('reload',"${app}/city_findCityByProvince.shtml?itemCode=" + value);
	}
  </script>
   </head>
  <body>
            省市:
     <input type="text" id="province" name="province" class="easyui-combobox"  editable="false" style="width: 120px;"/>
     <input type="text" id="city" name="city" class="easyui-combobox" editable="false" style="width: 120px;"/>
  </body>
</html>
