<%@ page import = "com.work.ChatApp.Model.*" %>
<%@ page import = "java.util.*" %>
<html>
<head>
    <title>Dashboard | ChatApp</title>

</head>
<body>
    <div>
    <h2>Dashboard</h2>
    
    <% for(Group g : (List<Group>)request.getAttribute("groups")){ %>
    <div><%=g.getGroupName()%> <a href="group/<%=g.getGroupid()%>">Join</a></div>
    <%}%>

    <div>${error}</div>
    <form action="/create-group" method="post">
        <input placeholder="Group Name" name="groupName" required />
        <input type="submit" value="Create Group" />
    </form>

    </div>
</body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    


</script>
</html>