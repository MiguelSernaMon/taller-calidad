package co.edu.udea.certificacion.sprint3.config;


public class AppConfig {

    private static String getConfiguredBaseUrl() {
        // Primero intenta leer desde webdriver.base.url (serenity.conf)
        String url = System.getProperty("webdriver.base.url");
        
        // Si no está configurado, intenta leer desde base.url (property del sistema)
        if (url == null || url.isEmpty()) {
            url = System.getProperty("base.url");
        }
        
        // Si aún no está configurado, usa el valor por defecto
        if (url == null || url.isEmpty()) {
            url = "https://innova-team-flow.vercel.app";
        }
        
        return url;
    }
    
    private static final String BASE_URL = getConfiguredBaseUrl();
    
    /**
     * Timeout por defecto en milisegundos para esperas.
     */
    public static final int DEFAULT_TIMEOUT = 3000;
    
    /**
     * Tiempo de delay para interacciones (en milisegundos).
     */
    public static final int INTERACTION_DELAY = 500;
    
    // ==================== RUTAS DE LA APLICACIÓN ====================
    
    /**
     * Obtiene la URL completa para la página de login.
     * @return URL de la página de login
     */
    public static String getLoginUrl() {
        return BASE_URL + "/";
    }
    
    /**
     * Obtiene la URL completa para el dashboard.
     * @return URL del dashboard
     */
    public static String getDashboardUrl() {
        return BASE_URL + "/dashboard";
    }
    
    /**
     * Obtiene la URL completa para la página de notificaciones.
     * @return URL de notificaciones
     */
    public static String getNotificacionesUrl() {
        return BASE_URL + "/dashboard";
    }
    
    /**
     * Obtiene la URL completa para la página de configuración de permisos.
     * @return URL de configuración de permisos
     */
    public static String getPermisosUrl() {
        return BASE_URL + "/permissions";
    }
    
    /**
     * Obtiene la URL completa para el perfil de usuario.
     * @return URL del perfil
     */
    public static String getPerfilUrl() {
        return BASE_URL + "/profile";
    }
    
    /**
     * Obtiene la URL completa para la gestión de equipos.
     * @return URL de equipos
     */
    public static String getEquiposUrl() {
        return BASE_URL + "/teams";
    }
    
    /**
     * Obtiene la URL completa para la gestión de proyectos.
     * @return URL de proyectos
     */
    public static String getProyectosUrl() {
        return BASE_URL + "/projects";
    }
    
    /**
     * Obtiene la URL base de la aplicación.
     * @return URL base
     */
    public static String getBaseUrl() {
        return BASE_URL;
    }
    
    /**
     * Obtiene una URL personalizada concatenando la ruta al base URL.
     * @param path Ruta relativa (ej: "/settings")
     * @return URL completa
     */
    public static String getUrl(String path) {
        if (path.startsWith("/")) {
            return BASE_URL + path;
        }
        return BASE_URL + "/" + path;
    }
}
