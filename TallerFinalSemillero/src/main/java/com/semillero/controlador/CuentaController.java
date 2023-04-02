package com.semillero.controlador;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semillero.entidades.Cuenta;
import com.semillero.servicios.CuentaServicio;


public class CuentaController extends HttpServlet{
    private CuentaServicio cuentaServicio;
    private ObjectMapper mapper;

    public CuentaController() {
        cuentaServicio = new CuentaServicio();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

            switch (path) {	
            	case"/listar":
                List<Cuenta> cuenta = CuentaServicio.listarCuenta();
                String json = mapper.writeValueAsString(cuenta);
                response.setContentType("application/json");
                response.getWriter().println(json);
                break;
                case "/buscar":
                    String identificador = request.getParameter("identificador");
                    try {
                        Cuenta cuenta1 = cuentaServicio.buscarcCuentas(identificador);
                        String json1 = mapper.writeValueAsString(cuenta1);
                        response.setContentType("application/json");
                        response.getWriter().println(json1);
                    } catch (Exception e) {
                        response.setStatus(404);
                        Map<String, String> error = new HashMap<>();
                        error.put("error", e.getMessage());
                        String json1 = mapper.writeValueAsString(error);
                        response.setContentType("application/json");
                        response.getWriter().println(json1);
                    }
                    break;
                default:
                    response.setStatus(404);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "No se encontro el recurso");
                    String json1 = mapper.writeValueAsString(error);
                    response.setContentType("application/json");
                    response.getWriter().println(json1);
                    break;
            }
            

        

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getContentType();
        
        String path = request.getPathInfo();

        switch (path) {	
        	case"/guardar":
                if (content != null && content.equals("application/json")) {
                    Map<String, Object> cuentaMap = mapper.readValue(request.getInputStream(), HashMap.class);
                    System.out.println(cuentaMap);
                    try {         
                        cuentaServicio.guardarCuenta(cuentaMap);
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        Map<String, String> respuesta = new HashMap<>();
                        respuesta.put("mensaje", "Cuenta guardada con exito");
                        String json = mapper.writeValueAsString(respuesta);
                        response.setContentType("application/json");
                        response.getWriter().println(json);
                        System.out.println(cuentaMap);

                    } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_CONFLICT);
                        Map<String, String> error = new HashMap<>();
                        error.put("error", e.getMessage());
                        String json = mapper.writeValueAsString(error);
                        response.setContentType("application/json");
                        response.getWriter().println(json);
                    }
            break;
            
        }default:
        	
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            Map<String, String> error = new HashMap<>();
            error.put("error", "El contenido debe ser JSON");
            String json = mapper.writeValueAsString(error);
            response.setContentType("application/json");
            response.getWriter().println(json);
            break;
            
        }   
        
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getContentType();

    			if(content == "application/json") {
    			Map <String, Object> personaMap = mapper.readValue(request.getInputStream(), HashMap.class);

    			try {
    				CuentaServicio.actualizarCuenta(personaMap);
    				response.setStatus(HttpServletResponse.SC_OK);
    				Map<String, String> respuesta = new HashMap<>();
    				respuesta.put("mensaje", "Persona actualizada con exito");
    				String json = mapper.writeValueAsString(respuesta);
    				response.setContentType("application/json");
    				response.getWriter().println(json);
                
    			} catch (Exception e) {
    				response.setStatus(HttpServletResponse.SC_CONFLICT);
    				Map<String, String> error = new HashMap<>();
    				error.put("error", e.getMessage());
    				String json = mapper.writeValueAsString(error);
    				response.setContentType("application/json");
    				response.getWriter().println(json);
    			}
    		
            
    		}else {
    			response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
    			Map<String, String> error = new HashMap<>();
    			error.put("error", "El contenido debe ser JSON");
    			String json = mapper.writeValueAsString(error);
    			response.setContentType("application/json");
    			response.getWriter().println(json);
       
       }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String identificador = request.getParameter("identificador");
        String path = request.getPathInfo();
        switch (path) {	
    		case"/eliminar":
    			try {
    				cuentaServicio.eliminarCuenta(identificador);
    				response.setStatus(HttpServletResponse.SC_OK);
    				Map<String, String> respuesta = new HashMap<>();
    				respuesta.put("mensaje", "Cuenta eliminada con exito");
    				String json = mapper.writeValueAsString(respuesta);
    				response.setContentType("application/json");
    				response.getWriter().println(json);
    			} catch (Exception e) {
    				response.setStatus(HttpServletResponse.SC_CONFLICT);
    				Map<String, String> error = new HashMap<>();
    				error.put("error", e.getMessage());
    				String json = mapper.writeValueAsString(error);
    				response.setContentType("application/json");
    				response.getWriter().println(json);
    			}
    			break;
    		default:
    			System.out.println("hubo errores");
    			break;
    }
 }
}
