<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	        table {
            width: 100%;
            border-spacing: 0;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin: 20px 0;
            font-size: 16px;
            text-align: left;
        }

        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
        }

        th:nth-child(1), th:nth-child(3) {
            background: linear-gradient(to right, #5ABFAA, #C8DDD9);
            color: #fff;
        }
        th:nth-child(2),th:nth-child(4) {
            background: linear-gradient(to left, #5ABFAA, #C8DDD9);
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f5f5f5;
        }

        tr:hover {
            background-color: #e2e2e2;
        }

        /* .checkbox-cell {
            text-align: center;
            display: none; /* 초기에는 체크박스를 숨김 처리 */
        } */
</style>
</head>
<body>
	<!-- /admin/user/hospital/edit.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
	<button type="button" id="btn" onclick="toggleCheckboxes()">버튼</button>

    <table>
        <thead>
            <tr>
                <th>컬럼1</th>
                <th>컬럼2</th>
                <th>컬럼3</th>
                <th>선택</th> <!-- 새로운 열 추가 -->
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>데이터1-1</td>
                <td>데이터1-2</td>
                <td>데이터1-3</td>
                <td class="checkbox-cell"><input type="checkbox"></td> <!-- 체크박스 추가 -->
            </tr>
            <tr>
                <td>데이터2-1</td>
                <td>데이터2-2</td>
                <td>데이터2-3</td>
                <td class="checkbox-cell"><input type="checkbox"></td>
            </tr>
            <tr>
                <td>데이터3-1</td>
                <td>데이터3-2</td>
                <td>데이터3-3</td>
                <td class="checkbox-cell"><input type="checkbox"></td>
            </tr>
        </tbody>
    </table>

    <script>
        function toggleCheckboxes() {
            var checkboxes = document.querySelectorAll('.checkbox-cell');
            checkboxes.forEach(function (checkbox) {
                checkbox.style.display = checkbox.style.display === 'none' ? 'table-cell' : 'none';
            });
        }
    </script>
</body>
</html>