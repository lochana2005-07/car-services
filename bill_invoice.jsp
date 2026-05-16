<%@ page import="model.Vehicle" %>
<%
    Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
    String customerName = (String) request.getAttribute("cName");
    String repairType = (String) request.getAttribute("rType");
    Double cost = (Double) request.getAttribute("cost");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Car Service Invoice</title>
    <style>
        .invoice-box { max-width: 500px; margin: 50px auto; padding: 30px; border: 1px solid #eee; box-shadow: 0 0 10px rgba(0, 0, 0, 0.15); font-family: Arial, sans-serif; border-radius: 8px; }
        .header { text-align: center; color: #333; margin-bottom: 20px; }
        .details { font-size: 16px; line-height: 1.8; color: #555; }
        .total { color: #28a745; font-size: 22px; font-weight: bold; margin-top: 15px; }
    </style>
</head>
<body>
    <div class="invoice-box">
        <h2 class="header">Car Service Invoice</h2>
        <hr>
        <div class="details">
            <% if (vehicle != null) { %>
                <p><strong>Vehicle No:</strong> <%= vehicle.getVehicleNumber() %></p>
                <p><strong>Brand / Model:</strong> <%= vehicle.getBrand() %> <%= vehicle.getModel() %></p>
                <p><strong>Customer Name:</strong> <%= customerName %></p>
                <p><strong>Service Rendered:</strong> <%= repairType %></p>
                <hr>
                <p class="total">Total Bill: Rs. <%= cost %>0</p>
            <% } else { %>
                <p>No Invoice Data Available.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
