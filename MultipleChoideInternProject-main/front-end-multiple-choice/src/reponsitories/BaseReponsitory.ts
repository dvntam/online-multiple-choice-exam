import { api } from "../services/http-common";
interface ResponseMeta {
  message: string;
}
interface PaginationData<T> {
  current_page: number;
  data: Array<T>;
  first_page_url: string;
  from: number;
  last_page: number;
  last_page_url: string;
  next_page_url: string;
  path: string;
  per_page: number;
  prev_page_url: string;
  to: number;
  total: number;
}
export interface PaginationResponse<T> {
  meta: ResponseMeta;
  data: PaginationData<T>;
}
interface AllResponse<T> {
  meta: ResponseMeta;
  data: Array<T>;
}
interface UpdateResponse<T> {
  meta: ResponseMeta;
  data: T;
}
interface DefaultResponse<T> {
  meta: ResponseMeta;
  data: T;
}
export abstract class BaseRepository<T> {
  public url: string;

  protected constructor(url: string) {
    this.url = url;
  }

  async all(queries?: object | undefined): Promise<AllResponse<T>> {
    let endpoint = `${this.url}`;
    if (queries) {
      const params: URLSearchParams = new URLSearchParams({
        all: "yes",
        ...queries,
      });
      endpoint += `?${params}`;
    } else {
      endpoint += `?all=yes`;
    }
    const { data } = await api.get(endpoint);

    return data as Promise<AllResponse<T>>;
  }

  async create(item: Omit<T, "id"> | object): Promise<T> {
    const { data } = await api.post(this.url, item);

    return data.data as Promise<T>;
  }

  async update(
    id: string | number,
    item: Partial<T>
  ): Promise<UpdateResponse<T>> {
    const { data } = await api.put(`${this.url}/${id}`, item);
    return data as Promise<UpdateResponse<T>>;
  }

  async delete(id: string | number): Promise<DefaultResponse<T>> {
    const { data } = await api.delete(`${this.url}/${id}`);
    return data as Promise<DefaultResponse<T>>;
  }

  async findOne(id: string | Partial<T>, queries: object = {}): Promise<T> {
    let endpoint = `${this.url}/${id}`;
    if (queries) {
      const params: URLSearchParams = new URLSearchParams({
        ...queries,
      });
      endpoint += `?${params}`;
    }
    const { data } = await api.get(endpoint);
    return data.data as Promise<T>;
  }

  async paginate(
    page: number = 1,
    queries: object = {}
  ): Promise<PaginationResponse<T>> {
    const params: URLSearchParams = new URLSearchParams({
      page: page.toString(),
      ...queries,
    });
    const { data } = await api.get(`${this.url}?${params}`);

    return data as Promise<PaginationResponse<T>>;
  }
}
