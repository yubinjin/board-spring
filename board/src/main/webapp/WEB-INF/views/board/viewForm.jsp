<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/header" />
<center>
<table style="width: 650px; ">
	<tr>
		<td style="width: 300px; height:40px;" valign="middle"><h2>${board.title }</h2></td>
		<td style="width: 350px; height:40px;" align="right" valign="bottom">${board.cTime }</td>
	</tr>
	<tr>
		<td colspan=2><hr/></td>
	</tr>
	<tr>
		<td  style="width: 300px; height:40px;" valign="top">${board.writer }</td>
		<td style="width: 350px; height:40px;" align="right" valign="top">
			<a href="download?fileName=${board.fileName }&writer=${board.writer }" >
				${board.fileName }
			</a>
		</td>
	</tr>
	<tr>
		<td colspan=2 style="width: 650px; height: 300px" valign="top">
		<pre>${board.content }</pre>
		</td>
	</tr>
	<tr>
		<td colspan=2><hr/></td>
	</tr>
	<tr>
		<td colspan=2 align="right">
			<input type=button style="width: 60px; " value='글쓰기'
			onclick="location.href='write'"/> 
			<c:if test="${sessionScope.id == board.writer }">
				<input type=button style="width: 60px; " value='수정'
				onclick="location.href='modify?bn=${board.bNumber}'"
				/>
				<input type=button style="width: 60px; " value='삭제'
				onclick="location.href='remove?bn=${board.bNumber}&fileName=${board.fileName }'"
				/>
			</c:if>
			<input type=button style="width: 60px; " value='목록'
			onclick="location.href='board'"
			/>
		</td>
	</tr>
</table>
</center>
<c:import url="/footer" />