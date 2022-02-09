package practica;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrada {
     
    @EqualsAndHashCode.Include
    private Integer id;   
    private String titulo;
    private String texto;
    private Date fecha;

    
}
