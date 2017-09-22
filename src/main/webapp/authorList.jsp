<%-- 
    Document   : authorList
    Created on : Sep 19, 2017, 8:35:49 PM
    Author     : mitchell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
    </head>
    <body>
        <h1>Author List</h1>
        
        <table border="1">
            <th>Author Id</th>
            <th>Author Name</th>
            <th>Dated Added</th>
            <c:forEach var="author" items="${authorList}">
                <tr >
                    <td> ${author.authorId}</td> 
                    <td> ${author.authorName}</td>  
                    <td> <fmt:formatDate pattern = "MM-dd-yyyy" value = "${author.dateAdded}" /> </td> 
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
