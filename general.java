/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import formatos.clase;
import formatos.formato;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Denilson
 */
@WebServlet(name = "general", urlPatterns = {"/general"})
public class general extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int expo= Integer.parseInt( request.getParameter("exponente"));
        int mati=Integer.parseInt(request.getParameter("matisa"));
        String modo=request.getParameter("mod").trim();
        List<clase> clases=new ArrayList<>();
        formato f=new formato();
        String [] r=(f.formatoGeneral(Double.parseDouble(request.getParameter("numero1").trim()),expo, mati));
           clase c=new clase();
           c.setSigno(r[0]);
           c.setExponente(r[1]);
           c.setMatisa(r[2]);
           c.setProcedimiento(f.res);
           c.setComplemento(modo);
           clases.add(c);
           String t=request.getParameter("numero2").trim();
          if (!t.isEmpty()) {
              double n2= Double.parseDouble(t);
              if (n2!=0) {
                  formato f2= new formato();
                  String [] r2=(f2.formatoGeneral(n2,expo, mati));
                    clase c2=new clase();
                    c2.setSigno(r2[0]);
                    c2.setExponente(r2[1]);
                    c2.setMatisa(r2[2]);
                    c2.setProcedimiento(f2.res);
                    c2.setComplemento(modo);
                    clases.add(c2);
              }
 
           }
 
           
           request.setAttribute("clases", clases);
           
           
       this.getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request, response);
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
