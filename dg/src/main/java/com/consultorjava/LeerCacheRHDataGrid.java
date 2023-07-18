package com.consultorjava;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class LeerCacheRHDataGrid {
    
    public static void ejecutar() {
       
       ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer().host("localhost").port(11222).security()
                .authentication()
                .username("buga")
                .password("buga");

        // Crear una instancia del administrador de caché remoto
        RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());

        // Obtener una referencia al caché de Red Hat Data Grid
        RemoteCache<String, String> dataGridCache = cacheManager.getCache("datagrid");

        
        // Recuperar un valor del caché
        String key = "31e0ff59-f443-40df-ac43-63126f92eff11";
        String value = dataGridCache.get(key);

        if (value != null) {
            System.out.println("Valor recuperado del cache: " + value);
        } else {
            System.out.println("La clave no existe en el cache");
        }

        

        // Cerrar el administrador de caché al finalizar
        cacheManager.stop();
    }
}
