package practica;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author @Alex13070
 * 
 * POJO de usuario
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Usuario {
    
    @EqualsAndHashCode.Include
    private String usuario;

    private String password;  

}