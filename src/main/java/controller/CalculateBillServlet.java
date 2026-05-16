package controller;

import model.Vehicle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculateBillServlet")
public class CalculateBillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. ටෙම්ප්ලේට් එකේ Form එකෙන් එන දත්ත ගන්නවා
        String vehicleNo = request.getParameter("vehicleNo");
        String brand = request.getParameter("brand");
        String modelName = request.getParameter("modelName");
        String yearStr = request.getParameter("year");
        String customerName = request.getParameter("customerName");
        String mechanic = request.getParameter("mechanic");
        String repairType = request.getParameter("repairType");

        int year = 0;
        if (yearStr != null && !yearStr.isEmpty()) {
            year = Integer.parseInt(yearStr);
        }

        // Vehicle Object එකක් හදනවා (OOP භාවිතය)
        Vehicle vehicleObj = new Vehicle(vehicleNo, brand, modelName, year);

        // රෙපයාර් එක අනුව ගාණ තීරණය කරන සරල ක්‍රමය
        double repairCost = 3000.00; 
        if (repairType != null) {
            if (repairType.equalsIgnoreCase("Full Service")) repairCost = 5500.00;
            else if (repairType.equalsIgnoreCase("Engine Repair")) repairCost = 15000.00;
            else if (repairType.equalsIgnoreCase("Brake Repair")) repairCost = 2500.00;
        }

        // 2. FILE HANDLING - Notepad (.txt) එකට ලියනවා
        String filePath = getServletContext().getRealPath("/") + "WEB-INF/classes/vehicles.txt";
        File file = new File(filePath);
        
        try (FileWriter fw = new FileWriter(file, true); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            String dataLine = vehicleObj.getVehicleNumber() + "," + 
                              vehicleObj.getBrand() + "," + 
                              vehicleObj.getModel() + "," + 
                              vehicleObj.getYear() + "," + 
                              customerName + "," + 
                              mechanic + "," + 
                              repairType + "," + 
                              repairCost;
                              
            bw.write(dataLine);
            bw.newLine(); 
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. දත්ත ටික බිල් එක පෙන්වන පිටුවට යවනවා
        request.setAttribute("vehicle", vehicleObj);
        request.setAttribute("cName", customerName);
        request.setAttribute("rType", repairType);
        request.setAttribute("cost", repairCost);

        request.getRequestDispatcher("bill_invoice.jsp").forward(request, response);
    }
}
