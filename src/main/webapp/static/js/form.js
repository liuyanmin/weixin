 (function(){
                
	function display(dom, command, callback){
		//to-do
		if(dom){
			if(dom instanceof Array){
				//console.log(dom.length)
				for(var i = 0; i < dom.length; i++){
					if(command == 'hide'){
						dom[i].style.display = 'none';
					}
			
					if(command == 'show'){
						dom[i].style.display = 'block';
					}
				}
			}else{
				if(command == 'hide'){
					dom.style.display = 'none';
				}
			
				if(command == 'show'){
					dom.style.display = 'block';
				}
			}
		}
		if(typeof callback == "function"){
			callback();
		}
	}
		
	function init(){
		var form2 = document.getElementById("form2");
		var form3 = document.getElementById("form3");
		var radio1 = document.getElementById("ptxt");
		var radio2 = document.getElementById("imgtxt");
		var radio3 = document.getElementById("audioMsg");
		var content = document.getElementById("weixincontent");
		var textarea = document.getElementById("content_txt");

		/* 文本消息 */
		if(weixinType && weixinType == 'text'){
			radio1.checked = true;
		}else if(weixinType && weixinType == 'news'){
			/* 图文消息 */
			radio2.checked = true;
			//form1.style.display = "none";
			content.style.display = "none";
			form2.style.display = "";
		}else if(weixinType && weixinType == 'music'){
			/* 语音消息  */
			radio3.checked = true;
			content.style.display = "none";
			form3.style.display = "";
		}else{
			radio1.checked = true;
			content.style.display = "";
			form1.style.display = "";
		}
		radio3.onclick = radio2.onclick = radio1.onclick = displayForm;
		function displayForm(){
			if(radio1.checked){
				radio1.value = "text";
				textarea.innerHTML = "";
				content.style.display = "";
				display([form2, form3], 'hide', null);
			}else if(radio2.checked){
				radio2.value = "news";
				//content.style.display = "none";
				textarea.innerHTML = "IMG,no content";
				form2.style.display = "";
				display([form3, content], 'hide', null);
			}else if(radio3.checked){
				radio3.value = "music";
				//content.style.display = "none";
				textarea.innerHTML = "AUDIO,no content";
				form3.style.display = "";
				display([form2, content], 'hide', null);
			}
		}
	}
	init();

     // 初始化日期控件
     $("#beginTime").datetimepicker({
         //默认语言
         language: 'zh-CN',
         format: "yyyy-mm-dd hh:ii:ss",
         autoclose: true,
         todayBtn: true,
         minView: 2,
         initialDate: new Date(),
         startDate: "2013-02-14 10:00"
     });

     // 初始化日期控件
     $("#endTime").datetimepicker({
         //默认语言
         language: 'zh-CN',
         format: "yyyy-mm-dd hh:ii:ss",
         autoclose: true,
         todayBtn: true,
         minView: 2,
         initialDate: new Date(),
         startDate: "2013-02-14 10:00"
     });
     
     $("#type").change(function () {
         var options = $("#type option:selected");
         var val = options.val();
         if (val == 0) {
             $("#beginTime").attr("readonly","readonly");
             $("#endTime").attr("readonly","readonly");
		 }
     });
 })();
