package mate.academy.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.lib.Injector;
import mate.academy.service.DriverService;

public class AuthenticationFilter implements Filter {
    private static final String DRIVER_ID = "driver_id";
    private static final Injector injector = Injector.getInstance("mate.academy");
    private static final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);
    private Set<String> urls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urls.add("/login");
        urls.add("/drivers/create");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        if (urls.contains(url)) {
            chain.doFilter(req, resp);
            return;
        }
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        if (driverId == null) {
            resp.sendRedirect("/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
