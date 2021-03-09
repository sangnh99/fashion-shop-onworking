package com.fashionshop.repository;

import com.fashionshop.entity.ItemSizeEntity;
import com.fashionshop.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemSizeRepository extends JpaRepository<ItemSizeEntity, Integer> {
    List<SizeEntity> findItemSizeEntitiesBySize(int sizeId);

    @Query(value = "SELECT new SizeEntity (s.id,s.name,s.isDeleted) from" +
            " ItemSizeEntity  i inner join SizeEntity  s on i.size.id = s.id   where i.amount >0 and i.item.id = ?1")
    List<SizeEntity> findItemSizeEntitiesByItem_Id(int itemId);

    @Query(value = "SELECT * from" +
            " ItemSizeEntity  where i.amount >0 and i.item.id = ?1", nativeQuery = true)
    List<ItemSizeEntity> findAllItemSizeEntitiesByItem_Id(int itemId);
}
