import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestorCuentas")
public class ControllerCuentas {

    @Autowired
    RestAdminCuentas proxy;

    @GetMapping("/{login}")
    public void getLogin(@PathVariable("login") String login){
        proxy.get(login);
    }

}
