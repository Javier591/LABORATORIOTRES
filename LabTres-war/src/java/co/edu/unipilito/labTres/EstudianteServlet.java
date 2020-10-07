/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipilito.labTres;

import co.edu.unipiloto.labTres.entity.Estudiante;
import static co.edu.unipiloto.labTres.entity.Estudiante_.lastname;
import co.edu.unipiloto.labTres.sesion.EstudianteFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CITIUS-PRESTAMO
 */
@WebServlet(name = "EstudianteServlet", urlPatterns = {"/EstudianteServlet"})
public class EstudianteServlet extends HttpServlet {
@EJB
private EstudianteFacadeLocal estudiantefacade ;

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
        response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter();
       /* String action = request.getParameter("action") ;
        String studetidstl = request.getParameter("studentId") ;
        
        Integer estudentid = new Integer(
        Integer.parseInt(studetidstl));
         String firsname = request.getParameter("firstName") ;
         String lasname = request.getParameter("lastName") ;
         String semes = request.getParameter("yearLevel") ;
         Integer  semestre = new Integer( Integer.parseInt(semes));
        
         System.out.println(firsname);
         System.out.println(lasname);
         System.out.println(semestre);
        
        Estudiante est = new Estudiante( estudentid, firsname,lasname,semestre ) ;
        request.setAttribute("student", est);
        
        ArrayList <Estudiante> estudiantes = new  ArrayList <Estudiante> ()  ;
            
        request.setAttribute("allStudents", estudiantes);
        
         if (action.equalsIgnoreCase("add") ){
             System.out.println("sumar");
         }
        */
        Integer studentId=new Integer(Integer.parseInt(request.getParameter("studentId")));
        
        String firstName=request.getParameter("firstName");
        System.out.println(""+firstName);
        String lastName=request.getParameter("lastName");
        System.out.println(""+lastName);
        Integer yearLever= new Integer( Integer.parseInt(request.getParameter("yearLevel")));
        Estudiante estudiante=new Estudiante(studentId,firstName,lastName,yearLever);       
        
        String action=request.getParameter("action");
        System.out.println("Action= "+action);
        if(action.equals("Add")){
           estudiantefacade.create(estudiante);
            System.out.println("Agregar");
        } else if(action.equals("Editar")){
              estudiantefacade.edit(estudiante);
            System.out.println("Editar");
        }else if(action.equals("Eliminar")){
             estudiantefacade.remove(estudiante);
            System.out.println("Eliminar");
        } else if(action.equals("Buscar")){
              estudiantefacade.find(out);
            System.out.println("Buscar");
        }
        
         request.setAttribute("stud", estudiante);
         request.setAttribute("allStudents", estudiantefacade.findAll());
         request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
      
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
