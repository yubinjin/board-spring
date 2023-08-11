<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/header" />
<center>
<table style="width: 650px; ">
	<thead>
	<tr>
		<th style="width: 330px; height:20px;" align="center">제 목</th>
		<th style="width: 80px; height:20px;" align="center">작성자</th>
		<th style="width: 120px; height:20px;" align="center">작성일</th>
		<th style="width: 80px; height:20px;" align="center">조회수</th>
	</tr>
	</thead>
	<tr>
		<td style="width: 330px; height:20px;" align="center"><hr/></td>
		<td style="width: 80px; height:20px;" align="center"><hr/></td>
		<td style="width: 120px; height:20px;" align="center"><hr/></td>
		<td style="width: 80px; height:20px;" align="center"><hr/></td>
	</tr>
	<c:forEach var="board" items="${boards }">
		<tr>
			<td style="width: 330px; height:40px;" align="center" 
			onclick="location.href='view?bn=${board.bNumber }'">${board.title }</td>
			<td style="width: 80px; height:40px;" align="center">${board.writer }</td>
			<td style="width: 120px; height:40px;" align="center">${board.cTime }</td>
			<td style="width: 80px; height:40px;" align="center">${board.vCount }</td>
		</tr>
	</c:forEach>
	<tr><td colspan=4><hr/></td></tr>
	<tr>
		<td colspan=4 align="right">
			<input type="button" value='삭제' style="width: 100px; "/>
			<input type="button" value='글쓰기' style="width: 100px; " 
			onclick="location.href='write' "/>
		</td>
	</tr>
	<tr><td colspan=5><hr/></td></tr>
</table>
이전 1 2 3 4 다음
<table>
<tr>
<td>
	<select>
		<option>전체</option>
		<option>제목</option>
		<option>작성자</option>
	</select>
	<input type=text name='search'/>
	<input type=button name='searchBtn' value='검색' style="width: 80px; "/>
</td>
</tr>
</table>
</center>

<c:import url="/footer" />