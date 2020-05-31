//
//  DownloadManager.swift
//  URL App
//
//  Created by Weiping Tok on 23/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import Foundation
import UIKit
import SwiftSoup

protocol DownloadManagerDelegate {
    func didUpdateItem(_ downloadManager: DownloadManager, download: ObjectModel)
    func didFailWithError(error: String)
}

struct DownloadManager {
    
    var delegate: DownloadManagerDelegate?
    
    func fetchHTML(urlString: String) {
        
        performRequest(with: urlString)
    }
    
    func performRequest(with urlString: String) {
        //1. Create a URL
        if let url = URL(string: urlString) {
            //2. Create a URLSession
            let session = URLSession(configuration: .default)
            //3. Give the session a task
            let task = session.dataTask(with: url) { (data, urlResponse, error) in
                if error != nil {
                    //print(error!)
                    self.delegate?.didFailWithError(error: error!.localizedDescription)
                    return
                }
                
                if let safeData = data {
                    //                    if let dataString = String(data: safeData, encoding: .utf8) {
                    //                        print(dataString)
                    //                    }
                    
                    if let download = self.parseHTML(safeData, urlString) {
                        self.delegate?.didUpdateItem(self, download: download)
                    }
                }
            }
            //4. Start the task
            task.resume()
        }
        
    }
    
    func parseHTML(_ htmlData: Data, _ urlString: String) -> ObjectModel? {
        
        var item = ObjectModel(url: urlString)
        
        // content of url
        //let html = "<html><head><title>First parse</title></head></html>"
        if let html = String(data: htmlData, encoding: .utf8) {
            //print(html)
            
            do {
                // parse it into a Document
                let doc = try SwiftSoup.parse(html)
                let title = try doc.select("title")
                let titleText: String = try title.text();
                item.title = titleText
                // link with rel
                let links = try doc.select("link[rel]")
                for link in links {
                    let linkHref: String = try link.attr("href")
                    if (linkHref.contains("http") && linkHref.contains(".png")) {
                        print(linkHref)
                        if let imageUrl = URL(string: linkHref) {
                            do {
                                let imageData: Data = try Data(contentsOf: imageUrl)
                                item.icon = UIImage(data: imageData)!
                            } catch {
                                print(error)
                            }
                        }
                        break
                    }
                    
                }
                
                //print(titleText)                
                
            } catch {
                print(error)
                self.delegate?.didFailWithError(error: error.localizedDescription)
                return nil
            }
            
        } else {
            print("Unable to get HTML source.")
            self.delegate?.didFailWithError(error: "Unable to get HTML source.")
            return nil
        }
        
        return item
        
    }
    
}
