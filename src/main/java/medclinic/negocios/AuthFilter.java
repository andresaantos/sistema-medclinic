package medclinic.negocios;

import medclinic.persistencia.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        if (uri.endsWith("login.html") || uri.endsWith("/api/auth/login")
                || uri.endsWith("/api/auth/logout") || uri.endsWith(".css") || uri.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("user") : null;

        if (usuario == null) {
            if (uri.startsWith("/api/")) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                resp.sendRedirect("/login.html");
            }
            return;
        }

        String role = usuario.getRole();
        if ("USER".equals(role)) {
            boolean permitidoUser = uri.endsWith("listarConsultas.html")
                    || uri.endsWith("/api/auth/me")
                    || (uri.startsWith("/api/consultas") && "GET".equalsIgnoreCase(req.getMethod()));

            if (permitidoUser) {
                chain.doFilter(request, response);
            } else if (uri.startsWith("/api/")) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                resp.sendRedirect("/listarConsultas.html");
            }
        } else if ("ADMIN".equals(role)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/login.html");
        }
    }
}