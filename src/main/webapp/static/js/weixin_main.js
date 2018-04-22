(function(){
	function init(){
		//to-do
		//var postcmd = document.getElementById("postcmdname");
		var cmdTable = document.getElementById("cmdtable");
		if(cmdTable){
			//to-do
			var cmdList = cmdTable.getElementsByTagName("tr"); 
			var trNum = cmdList.length - 1;
			//alert(trNum);
			var form = [];
			for(var i = 0; i < trNum; i++){
				//alert(i);
				//form.push(document.getElementById('postcmdname'+i));
				var cmdName = cmdList[i].getElementsByTagName("td");
				//document.getElementById("modif"+i).onclick =  function(){return function(j){document.getElementById('postcmdname'+j).submit(); window.location = "mod.php";}(i)};
				document.getElementById("delete"+i).onclick = function(){window.location = "delete.php"};
			}
		}
	}

	init();
})();
