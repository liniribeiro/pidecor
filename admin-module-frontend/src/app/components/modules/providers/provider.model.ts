export class Provider {
    constructor(
        public id: string,
        public name: string,
        public cnpj: string,
        public address: string,
        public city: string,
        public phone: string,
        public email: string,
        public providersAPIId?: string,
        public social_name?: string,
        public homepage?: string,
        public companyType?: string,
        public annual_billing?: string,
        public foundation_year?: string,
        public qtd_clients?: number,
        public reputation?: string,
        public category?: string,
        public qtd_items?: number,
        public percentage?: number,
        public rejection_rate?: number,
        public qtd_items_sold?: number,
        public status?: string
    ){}
}