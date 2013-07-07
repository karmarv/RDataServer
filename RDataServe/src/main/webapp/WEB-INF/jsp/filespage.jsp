<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FILES</title>
</head>
<body>
<h1>Files</h1>

<table style="border: 1px solid; width: 500px; text-align:center">
	<thead style="background:#fcf">
		<tr>
			<th>File ID</th>
			<th>File Name</th>
			<th>File Size</th>
			<th>Type</th>
			<th colspan="4"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${files}" var="file">
			<c:url var="editUrl" value="/ss/main/files/edit?fileId=${file.fileId}" />
			<c:url var="deleteUrl" value="/ss/main/files/delete?fileId=${file.fileId}" />
			<c:url var="addUrl" value="/ss/main/files/add" />
		<tr>
			<td><c:out value="${file.fileId}" /></td>
			<td><c:out value="${file.fileName}" /></td>
			<td><c:out value="${file.filePath}" /></td>
			<td><c:out value="${file.fileSizeInKB}" /></td>
			<td><a href="${editUrl}">Edit</a></td>
			<td><a href="${deleteUrl}">Delete</a></td>
			<td><a href="${addUrl}">Add</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</body>
</html>