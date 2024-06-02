import { EventEmitter, Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class SharingDataService {


    private _handlerLoginEventEmitter = new EventEmitter();
    private _handlerSingUpEventEmitter = new EventEmitter();

    constructor() {}

    get handlerLoginEventEmitter() {
      return this._handlerLoginEventEmitter;
    }

    get handlerSingUpEventEmitter() {
      return this._handlerSingUpEventEmitter;
    }
}
