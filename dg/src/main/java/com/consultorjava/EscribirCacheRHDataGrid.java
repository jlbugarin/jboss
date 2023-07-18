package com.consultorjava;

import java.util.UUID;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class EscribirCacheRHDataGrid {

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

        // escribir en la cache

        String valueCache = "pollox";
        
        for (int i=0;i<1000000;i++){
        UUID uuid = UUID.randomUUID();

        String key = uuid.toString();

        dataGridCache.put(key, valueCache);
        
        }
        System.out.println("Datos escritos en la caché.");
        // Cerrar el administrador de caché al finalizar
        cacheManager.stop();
    }
}
