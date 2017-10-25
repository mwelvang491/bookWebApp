<%-- 
    Document   : addAuthor
    Created on : Oct 19, 2017, 5:30:32 AM
    Author     : mitchell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Author</title>
    </head>
    <body>
        <form method="POST">
        <h2>Add Author</h2>
        
        <label>Author Name: <input type="text"  value="Steve Jobs" name="authorName" /></label><br><br>
            
        <label> Date Added:  <input type="text" value="10-10-1992" name="dateAdded" /></label><br><br>
        
        <a href="authorAddAuthorController?action=add" />CREATE</a><br /><br />
        
        <a href="index.jsp">back</a>
    </form>
    </body>
</html>
