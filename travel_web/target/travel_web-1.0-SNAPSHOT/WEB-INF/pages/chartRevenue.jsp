<%-- 
    Document   : chartRevenue
    Created on : Aug 22, 2023, 1:40:03 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            #myChart {
                width: 800px !important;
                height: 420px !important;
            }
            .chart-container {
                justify-content: center;
            }
            .container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            .centered-input {
                text-align: center;
                width: 200px;
            }
            .quater{
                display:  flex;
                margin-top: 10px;
                justify-content: center;
            }
            .quater div {
                margin-left: 8px;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center; margin-top: 10px">Thống kê doanh thu</h1>
        <form class="d-flex justify-content-end">
            <input class="form-control me-2" type="text" name="year" id="yearInput" placeholder="Nhập năm..." oninput="toggleSubmitButton()">
            <button class="btn btn-primary" style="width:150px" type="submit" id="submitButton" disabled>Nhập năm</button>
        </form>
        <div class="chart-container">
            <canvas id="myChart"></canvas>     
            <div class="quater">
                <div>
                    <p>Doanh thu quí I</p>
                    <input id="quaterI" type="text"  style="text-align: center"/>
                </div> 
                <div>
                    <p>Doanh thu quí II</p>
                    <input id="quaterII" type="text"  style="text-align: center"/>
                </div> 
                <div>
                    <p>Doanh thu quí III</p>
                    <input id="quaterIII" type="text"  style="text-align: center"/>
                </div> 
                <div>
                    <p>Doanh thu quí IV</p>
                    <input id="quaterIV" type="text"  style="text-align: center"/>
                </div> 
            </div>
        </div>
        <div>
            <p style="text-align: center;">Tổng doanh thu trong năm</p>
            <input id="totalSumInput" type="text" value="${totalSum}" style="text-align: center"/>
        </div>          

        <script>
            function toggleSubmitButton() {
                const yearInput = document.getElementById("yearInput");
                const submitButton = document.getElementById("submitButton");

                if (yearInput.value.trim() === "") {
                    submitButton.disabled = true;
                } else {
                    submitButton.disabled = false;
                }
            }

            const formatter = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var totalSum = 0;
            var quaterI = 0;
            var quaterII = 0;
            var quaterIII = 0;
            var quaterIV = 0;
            var totalSoldToursArray = [
            <c:forEach items="${chartRevenue}" var="value">
                ${value}<c:if test="${not loop.last}">,</c:if>
            </c:forEach>];
            for (var i = 0; i < totalSoldToursArray.length; i++) {
                totalSum += parseInt(totalSoldToursArray[i]);
            }
            document.getElementById("totalSumInput").value = formatter.format(totalSum);
            if (totalSoldToursArray.length > 0) {
                quaterI = (typeof totalSoldToursArray[0] !== 'undefined' ? totalSoldToursArray[0] : 0) +
                        (typeof totalSoldToursArray[1] !== 'undefined' ? totalSoldToursArray[1] : 0) +
                        (typeof totalSoldToursArray[2] !== 'undefined' ? totalSoldToursArray[2] : 0);
            }
            if (totalSoldToursArray.length > 0) {
                quaterII = (typeof totalSoldToursArray[3] !== 'undefined' ? totalSoldToursArray[3] : 0) +
                        (typeof totalSoldToursArray[4] !== 'undefined' ? totalSoldToursArray[4] : 0) +
                        (typeof totalSoldToursArray[5] !== 'undefined' ? totalSoldToursArray[5] : 0);
            }
            if (totalSoldToursArray.length > 0) {
                quaterIII = (typeof totalSoldToursArray[6] !== 'undefined' ? totalSoldToursArray[6] : 0) +
                        (typeof totalSoldToursArray[7] !== 'undefined' ? totalSoldToursArray[7] : 0) +
                        (typeof totalSoldToursArray[8] !== 'undefined' ? totalSoldToursArray[8] : 0);
            }
            if (totalSoldToursArray.length > 0) {
                quaterIV = (typeof totalSoldToursArray[9] !== 'undefined' ? totalSoldToursArray[9] : 0) +
                        (typeof totalSoldToursArray[10] !== 'undefined' ? totalSoldToursArray[10] : 0) +
                        (typeof totalSoldToursArray[11] !== 'undefined' ? totalSoldToursArray[11] : 0);
            }
            document.getElementById("quaterI").value = formatter.format(quaterI);
            document.getElementById("quaterII").value = formatter.format(quaterII);
            document.getElementById("quaterIII").value = formatter.format(quaterIII);
            document.getElementById("quaterIV").value = formatter.format(quaterIV);

            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8',
                        'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                    datasets: [{
                            label: 'Doanh thu (VND)',
                            data: totalSoldToursArray,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </body>
</html>
