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
        <form id="form1" name="form1" method="get" action="authorController">
        <h1>Author List</h1>
        
        <table border="1">
            <th>Author Id  </th>
            <th>Author Name</th>
            <th>Dated Added</th>
            <th style="width: 40px;"></th>
            <c:forEach var="author" items="${authorList}">
                <tr >
                    <td >${author.authorId}</td>
                    <td><a href="authorController?action=updatebyPrimarKey&Id='${author.authorId}'">${author.authorName}</a> </td>  
                    <td> <fmt:formatDate pattern = "MM-dd-yyyy" value = "${author.dateAdded}" /> </td> 
                    <td><input type="checkbox" value="${author.authorId}"  name="boxes"    /> </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        
        
        <a href="authorController?action=delete" />DELETE</a><br /><br />
        <a href="authorController?action=updatebyPrimaryKey" />Update</a><br /><br />
        <a href="authorController?action=listAll">View All Authors</a><br />
        
        <p> <c:out value="${test}"></c:out></p>
        
    
        
        
    <h2>Add Author</h2>
    
    
            <label>Author Name: <input type="text"  value="Steve Jobs" name="authorName" id="authorName" /></label><br><br>

            <label> Date Added:  <input type="text" value="10-10-1992" name="dateAdded" id="dateAdded" /></label><br><br>
            <input type="hidden" value="add" name="action" />
            <input type="submit" name="Submit" value="Update">
            
            
       
    </form>
    <a href="index.jsp">back</a>

    
    
    </body>
</html>
