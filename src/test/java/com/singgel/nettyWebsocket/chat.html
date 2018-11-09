<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Socket</title>
    <script type="text/javascript">
        var websocket;

        //如果浏览器支持WebSocket
        if(window.WebSocket){
            websocket = new WebSocket("ws://localhost:8989/ws");  //获得WebSocket对象

            //当有消息过来的时候触发
            websocket.onmessage = function(event){
                var respMessage = document.getElementById("respMessage");
                respMessage.value = respMessage.value + "\n" + event.data;
            }

            //连接关闭的时候触发
            websocket.onclose = function(event){
                var respMessage = document.getElementById("respMessage");
                respMessage.value = respMessage.value + "\n断开连接";
            }

            //连接打开的时候触发
            websocket.onopen = function(event){
                var respMessage = document.getElementById("respMessage");
                respMessage.value = "建立连接";
            }
        }else{
            alert("浏览器不支持WebSocket");
        }

        function sendMsg(msg) { //发送消息
            if(window.WebSocket){
                if(websocket.readyState == WebSocket.OPEN) { //如果WebSocket是打开状态
                    websocket.send(msg); //send()发送消息
                }
            }else{
                return;
            }
        }
    </script>
</head>
<body>
<form onsubmit="return false">
    <textarea style="width: 300px; height: 200px;" name="message"></textarea>
    <input type="button" onclick="sendMsg(this.form.message.value)" value="发送"><br>
    <h3>信息</h3>
    <textarea style="width: 300px; height: 200px;" id="respMessage"></textarea>
    <input type="button" value="清空" onclick="javascript:document.getElementById('respMessage').value = ''">
</form>
</body>
</html>