export interface DetailedTour {
    id: number,
    name: string,
    start: Date,
    duration: number,
    price: number,
    museumId: number
    museum: {
        museumId: number;
        name: string;
        address: string;
        phone: string;
        city: string;
        country: string;
        type: string;
        lat: number;
        lng: number;
    }
}