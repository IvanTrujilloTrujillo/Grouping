import { Site } from "./site";

export class SiteWithReview extends Site {

  constructor(
    _id: number,
    _name: string,
    _mapUrl: string,
    _tocken: string | null,
    private _meanReviews: number
  ) {
    super(_id, _name, _mapUrl, _tocken);
  }

  public get meanReviews(): number {
    return this._meanReviews;
  }
  public set meanReviews(value: number) {
    this._meanReviews = value;
  }
}
