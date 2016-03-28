<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
  </head>
  <body> 
   
   
   <input type="button" value="Emp转JSON"/><p>

   <input type="button" value="List<Emp>转JSON"/><p>

   <input type="button" value="Map<String,Object>转JSON"/><p>
   
   
   
   <!-- Map<String,Object>转JSON -->	  
   <script type="text/javascript">
   		$(":button:last").click(function(){
   			var url = "${pageContext.request.contextPath}/emp/map2json.action";
   			var sendData = null;
			$.post(url,sendData,function(backData,textStaut,ajax){
				alert(ajax.responseText);
			});		
   		});
   </script>
   
   
   
   <!-- List<Emp>转JSON -->	  
   <script type="text/javascript">
   		$(":button:eq(1)").click(function(){
   			var url = "${pageContext.request.contextPath}/emp/listbean2json.action";
   			var sendData = null;
			$.post(url,sendData,function(backData,textStaut,ajax){
				alert(ajax.responseText);
			});		
   		});
   </script>
   
   
   
   <!-- Emp转JSON -->	  
   <script type="text/javascript">
   		$(":button:first").click(function(){
   			var url = "${pageContext.request.contextPath}/emp/bean2json.action";
   			var sendData = null;
			$.post(url,sendData,function(backData,textStaut,ajax){
				//alert(ajax.responseText);
				var hiredate = backData.hiredate;
				var date = new Date(hiredate);
				alert(date.getFullYear()+"年"+(date.getMonth()+1)+"月"+(date.getDate())+"日");
			});		
   		});
   </script>
   
  </body>
</html>









