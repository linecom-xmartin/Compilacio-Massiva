package com.linecom.compilacionmasiva.bbva.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.TicFuentesEntornos;
import com.linecom.compilacionmasiva.bbva.entity.TicFuentesEntornosId;

@Repository
public interface TicFuentesEntornosRepository extends JpaRepository<TicFuentesEntornos, TicFuentesEntornosId> {

}
