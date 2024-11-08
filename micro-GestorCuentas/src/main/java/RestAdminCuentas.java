import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="micro-AdminCuentas")
@RequestMapping("gestorCuentas")
public interface RestAdminCuentas {

    @GetMapping(path="/{login}")
    public void get(@PathVariable( "login" ) String login );
}
