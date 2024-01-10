import java.util.ArrayList;
import java.util.List;

public class ProduitService {
    private List<Produit> produits = new ArrayList<>();

    public void create(Produit nouveauProduit) {
        if (produitExisteDeja(nouveauProduit.getId()) || produitExisteDeja(nouveauProduit.getNom())) {
            throw new IllegalArgumentException("Un produit avec le même ID ou nom existe déjà.");
        }

        if (nouveauProduit.getPrix() <= 0 || nouveauProduit.getQuantite() <= 0) {
            throw new IllegalArgumentException("Le prix et la quantité doivent être positifs.");
        }

        produits.add(nouveauProduit);
    }

    public Produit read(Long id) {
        return produits.stream()
                .filter(produit -> produit.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Produit produitMaj) {
        if (produitExisteDeja(produitMaj.getId()) || produitExisteDeja(produitMaj.getNom())) {
            throw new IllegalArgumentException("Un autre produit avec le même ID ou nom existe déjà.");
        }

        if (produitMaj.getPrix() <= 0 || produitMaj.getQuantite() <= 0) {
            throw new IllegalArgumentException("Le prix et la quantité doivent être positifs.");
        }

        Produit existant = read(produitMaj.getId());
        if (existant != null) {
            existant.setNom(produitMaj.getNom());
            existant.setPrix(produitMaj.getPrix());
            existant.setQuantite(produitMaj.getQuantite());
        } else {
            throw new IllegalArgumentException("Le produit à mettre à jour n'existe pas.");
        }
    }

    public void delete(Long id) {
        Produit produitASupprimer = read(id);
        if (produitASupprimer != null) {
            produits.remove(produitASupprimer);
        } else {
            throw new IllegalArgumentException("Le produit à supprimer n'existe pas.");
        }
    }

    private boolean produitExisteDeja(Long id) {
        return produits.stream().anyMatch(produit -> produit.getId().equals(id));
    }

    private boolean produitExisteDeja(String nom) {
        return produits.stream().anyMatch(produit -> produit.getNom().equals(nom));
    }
}