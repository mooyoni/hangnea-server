package kr.hhplus.be.server.points.service;

import kr.hhplus.be.server.points.domain.PointsEntity;
import kr.hhplus.be.server.points.domain.PointsRepository;
import kr.hhplus.be.server.points.dto.PointChargeRequest;
import kr.hhplus.be.server.points.dto.PointUseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class PointsService {
    private final PointsRepository pointsRepository;

    @Transactional
    public void chargePoints(PointChargeRequest request) {
        PointsEntity entity = pointsRepository.findByUserId(request.userId())
                .orElse(new PointsEntity(1L, request.userId(), 0));

        entity.addPoints(request.amount());
        pointsRepository.save(entity);
    }

    @Transactional
    public void processPayment(PointUseRequest request) {
        PointsEntity entity = pointsRepository.findByUserId(request.userId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User points not found for userId: " + request.userId()));

        if (entity.getPoints() < request.amount()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Insufficient points for payment");
        }

        entity.usePoints(request.amount());
        pointsRepository.save(entity);
    }
}
