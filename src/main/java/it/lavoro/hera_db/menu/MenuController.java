package it.lavoro.hera_db.menu;

import it.lavoro.hera_db.auth.locations.Location;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Validated
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public List <Piatto> getMenu() {
        return menuService.getMenu();
    }

    @PostMapping("/piatto")
    @ResponseStatus(HttpStatus.CREATED)
    public Piatto create(@RequestBody @Valid PiattoRequest piattoRequest, @RequestParam Categoria categoria, @AuthenticationPrincipal Location location) {
        return menuService.create(piattoRequest, categoria, location);
    }

    @PutMapping("/piatto/{id}")
    public Piatto update(@PathVariable Long id, @RequestBody @Valid PiattoRequest piattoRequest, @RequestParam Categoria categoria,@AuthenticationPrincipal Location location) {
        return menuService.update(id, piattoRequest, categoria, location);
    }

    @DeleteMapping("/piatto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id,@AuthenticationPrincipal Location location) {
        menuService.delete(id, location);
    }

}
