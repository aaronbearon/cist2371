<!-- ComputeTax.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Locale" %>
<jsp:useBean id="taxBeanId"
             class="aaronbearon.chapter38.ComputeTax" scope="request">
</jsp:useBean>
<jsp:setProperty name="taxBeanId" property="*"/>
<html>
<head>
    <title>Compute Tax</title>
</head>
<body>

<h3>Compute Tax</h3>

<form method="post">
    <label for="income">Taxable Income:</label>
    <input name="income" id="income" value="<jsp:getProperty name="taxBeanId" property="income"/>"/>
    <br/><br/>

    <label for="status">Filing Status:</label>
    <select name="status" id="status">
        <option value="0"<%= 0 == taxBeanId.getStatus() ? "selected" : "" %>>Single</option>
        <option value="1"<%= 1 == taxBeanId.getStatus() ? "selected" : "" %>>Married Filing Jointly</option>
        <option value="2"<%= 2 == taxBeanId.getStatus() ? "selected" : "" %>>Married Filing Separately</option>
        <option value="3"<%= 3 == taxBeanId.getStatus() ? "selected" : "" %>>Head of HouseHold</option>
    </select>
    <br/><br/>

    <input type="submit" value="Compute Tax">
    <br/><br/>
</form>

<div style="color: red;">
    Taxable Income:
    <% NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);%>
    <%= currencyFormat.format(taxBeanId.getIncome())%>
    <br/><br/>

    Filing Status:
    <%= taxBeanId.getStatus() %>
    <br/><br/>

    Tax:
    <%= currencyFormat.format(taxBeanId.computeTax())%>
    <br/><br/>
</div>

</body>
</html>
