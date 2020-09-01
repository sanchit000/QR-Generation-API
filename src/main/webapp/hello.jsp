<%@page import="com.example.repeat4.EncodeInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <table class="table table-striped table-dark">
    <thead>
    <tr>
    <th   scope="col">index</th>
      <th scope="col">Key</th>
      <th scope="col">Value</th>
    </tr>
  </thead>
  <tbody>
  
 
<c:forEach var="encode" items="${encodeinfo}" varStatus="count">
    <c:set var="datatype" value="${encode.data_type}"/>
    <c:set var="comapre" value="url"/>
    <c:if test ="${datatype == 'url'}">
    <tr>
    <th scope="row">${count.index}</th>
    <td><c:out value="${encode.key} :"/></td>
    <td><a  href="<c:url value="${encode.value}"/>"><c:out value="${encode.value} :"/></a></td>
    </tr>
    </c:if>
    <c:if test ="${datatype != 'url'}">
	<tr>
 <th scope="row">${count.index}</th>
    <td><c:out value="${encode.key} :"/></td>
    <td><c:out value="${encode.value}"/></td>
    </tr>
    </c:if>
</c:forEach>

</tbody>
</table>
</body>
</html>
