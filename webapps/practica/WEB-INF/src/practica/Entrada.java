package practica;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrada {
     
    @EqualsAndHashCode.Include
    private Integer id;   
    private String titulo;
    private String texto;
    private Date fecha;

    
}
