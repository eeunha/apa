<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
        h1 {
            margin-top: 40px;
            font-size: 40px;
            text-align: center;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 0 auto;
            margin-bottom: 20px;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
            font-size: 1.1rem;
        }
        th {
            background-color: #f2f2f2;
            width: 12%;
        }

		table tr:nth-child(1) th:nth-child(1) {
			width: 40%;
		}

		table tr:nth-child(1) th {
			text-align:center;
		}

        #info-submit-btn {
            text-align: center;
            border: 0;
        	display: flex;
        	margin: 0 auto;
            font-size: 1.2rem;
            background: #5bc1ac;
            color: white;
            padding: 10px;
            cursor: pointer;
            border-radius: 10px;
            width: 100px;
            justify-content: center
        }
        
        #btn-box {
        	width: 80%;
        	margin: 0 auto;
        }
        
        label {
	        line-height: 50px;
		    width: 100%;
		    height: 50px;
        }
        
        #box-20 {
        	height: 20px;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<!-- /selftest/test.jsp -->
<main id="main">
    <h1 class="sub" id="self-test-title"></h1>

    <form action="/apa/selftest/test.do" method="POST" onsubmit="return validateForm();">
        <input type="hidden" name = "seq" value="${seq}">
        <table>
            <tr>
                <th>질문</th>
                <th>전혀 없었다</th>
                <th>거의 없었다</th>
                <th>때때로 있었다</th>
                <th>자주 있었다</th>
                <th>매우 자주 있었다</th>
            </tr>
            <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list.mediTestQuestionNo}. ${list.mediTestQuestionContent}</td>
                 	<td style="text-align:center;"><label><input type="radio" name="q${list.mediTestQuestionNo}" value="0"/></label></td>
                    <td style="text-align:center;"><label><input type="radio" name="q${list.mediTestQuestionNo}" value="1"/></label></td>
                    <td style="text-align:center;"><label><input type="radio" name="q${list.mediTestQuestionNo}" value="2"/></label></td>
                    <td style="text-align:center;"><label><input type="radio" name="q${list.mediTestQuestionNo}" value="3"/></label></td>
                    <td style="text-align:center;"><label><input type="radio" name="q${list.mediTestQuestionNo}" value="4"/></label></td>
                </tr>
            </c:forEach>
        </table>
        <div id="box-20"></div>
        <div id="btn-box"><input id="info-submit-btn" type="submit" value="제출"></div>
    </form>
<div id="box-20"></div>
<div id="box-20"></div>
</main>


<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<!-- 추가 질문들... -->
<script>

    function validateForm() {
        var radios = document.getElementsByTagName('input');
        var validation = {};

        for (var i = 0; i < radios.length; i++) {
            if (radios[i].type === 'radio' && !validation[radios[i].name]) {
                validation[radios[i].name] = {checked: false, count: 0};
            }
            if (radios[i].type === 'radio') {
                validation[radios[i].name].count++;
                if (radios[i].checked) {
                    validation[radios[i].name].checked = true;
                }
            }
        }

        for (var key in validation) {
            if (validation[key].count > 1 && validation[key].checked === false) {
                alert('모든 문항을 작성해주세요.');
                return false;
            }
        }

        // todo: radio button checked value
        var total = 0;
        var radios

        return true;
    }

</script>
</body>
</html>