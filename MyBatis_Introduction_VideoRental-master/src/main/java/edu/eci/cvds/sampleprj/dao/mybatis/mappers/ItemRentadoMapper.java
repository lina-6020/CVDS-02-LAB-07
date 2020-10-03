
package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.TipoItem;
import java.util.Date;
import java.util.List;


public interface ItemRentadoMapper {
    public List<TipoItem> getItemsRentados();
    
    public TipoItem getItemRentado(int id);
    
    public void addItemRentado(int cliid,int itemid,Date fechaini,Date fechafin);
}
