<html>
<head>
    <title>Dashboard | ChatApp</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <style>
        .notification{
            display: hidden;
            position:absolute;
            right:20px;
            z-index: 1;
            top:20px;

        }
    </style>

</head>
<body>

    <span id="notification" class="notification"></span>
    <div>
    <h2>Group Chat : ${group.groupName}</h2>
    <div>
        <input placeholder="UserName" id="username" />
        <button id="addUserBtn">Add</button>
    </div>
    <div>
        <input placeholder="message" id="message" />
        <button id="button">SEND</button>
        <div id="result"></div>
    </div>          
    </div>
</body>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    var ws = new SockJS("/wb-endpoint");
    var client = Stomp.over(ws);

    client.connect({},onConnect,onError);

    function onError(){
        console.log("Error in Connection");
    }

    function onConnect(){

        console.log("connected!!!");
        client.subscribe("/topic/message/${group.groupid}",onRecieve);
        client.subscribe("/topic/notify/${group.groupid}",onRecieveNotification);
    }

    function onRecieveNotification(data) {
        var data=JSON.parse(data.body);
        
        
        showNotification(data.message);
    }

    function showNotification(message){

        $(".notification").css("display","block");
        $(".notification").append("<div class=\"alert alert-primary\" role=\"alert\">"+message+"</div>");
        
        
        disNotify();
    }

    function disNotify(){
        setTimeout(() => {
            console.log("hidden");
            $(".notification").empty().css("display","hidden");

        }, 2000);
    }

    function onRecieve(data){
        var data=JSON.parse(data.body);
        console.log(data);
        addMessage(data);
    }

    function addMessage(data){
        // console.log(data.message);
       $("#result").append("<div>"+data.sender+" : "+data.message+"</div>"); 
    }

    $("#button").click(()=>{
        // send Message
        var mes = $("#message").val();
        var data = {sender:"${user.userName}", message:mes,type:"SEND"};

        client.send("/app/group.message/${group.groupid}",{},JSON.stringify(data));

    });

    $("#addUserBtn").click(()=>{
        var username = $("#username").val();
        var data = {userName:username};
        client.send("/app/group.add/${group.groupid}",{},JSON.stringify(data));
    });


</script>
</html>