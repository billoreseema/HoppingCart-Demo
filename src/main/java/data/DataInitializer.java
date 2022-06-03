package data;

import model.Offer;
import model.Product;
import repository.OfferRepository;
import repository.ProductRepository;

public class DataInitializer {

    public static void dataInit(ProductRepository productRepository, OfferRepository offerRepository) {
        productRepository.addProduct(new Product("A", 20D));
        productRepository.addProduct(new Product("B", 30D));
        productRepository.addProduct(new Product("C", 40D));
        productRepository.addProduct(new Product("D", 50D));

        offerRepository.addOffer(new Offer("A", 3, 15D, Boolean.TRUE));
        offerRepository.addOffer(new Offer("B", 2, 25D, Boolean.TRUE));
        offerRepository.addOffer(new Offer("C", 4, 30D, Boolean.TRUE));
        offerRepository.addOffer(new Offer("D", 1, 25D, Boolean.TRUE));

    }
}
