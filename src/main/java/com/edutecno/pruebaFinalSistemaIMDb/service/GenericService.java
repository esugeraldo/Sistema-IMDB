package com.edutecno.pruebaFinalSistemaIMDb.service;

import java.util.List;

public interface GenericService<Entity>  {
    
   public void update(Entity entity);
   public void delete(Entity entity);
   public Entity findById(Long id);
   public List<Entity> findALL();
   public void create(Entity entity);
}
