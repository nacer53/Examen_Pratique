import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProduitServiceTest {
    private ProduitService produitService;

    @BeforeEach
    public void setUp() {
        produitService = new ProduitService();
    }

    @Test
    public void testCreateProduit() {
        Produit produit = new Produit(1L, "Ordinateur", 999.99, 10);
        produitService.create(produit);
        assertEquals(produit, produitService.read(1L));
    }

    @Test
    public void testUpdateProduit() {
        Produit produit = new Produit(2L, "Téléphone", 799.99, 20);
        produitService.create(produit);

        produit.setQuantite(30);
        produitService.update(produit);

        Produit produitMaj = produitService.read(2L);
        assertEquals(30, produitMaj.getQuantite());
    }

    @Test
    public void testDeleteProduit() {
        Produit produit = new Produit(3L, "Tablette", 499.99, 15);
        produitService.create(produit);
        produitService.delete(3L);
        assertNull(produitService.read(3L));
    }
}