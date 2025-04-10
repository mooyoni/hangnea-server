package kr.hhplus.be.server.points.service;

import kr.hhplus.be.server.common.enums.ChargeDirection;
import kr.hhplus.be.server.points.domain.PointsEntity;
import kr.hhplus.be.server.points.domain.PointsHistoryEntity;
import kr.hhplus.be.server.points.domain.PointsHistoryRepository;
import kr.hhplus.be.server.points.domain.PointsRepository;
import kr.hhplus.be.server.points.dto.PointChargeRequest;
import kr.hhplus.be.server.points.dto.PointUseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PointsService {
    private final PointsRepository pointsRepository;
    private final PointsHistoryRepository pointsHistoryRepository;

    @Transactional
    public void chargePoints(PointChargeRequest request) {
        PointsEntity entity = pointsRepository.findByUserId(request.userId())
                .orElse(new PointsEntity(null, request.userId(), 0));

        entity.addPoints(request.amount());
        pointsRepository.save(entity);

        PointsHistoryEntity pointsHistoryEntity = new PointsHistoryEntity(
                request.userId(),
                request.amount(),
                ChargeDirection.PLUS,
                LocalDateTime.now()
        );
        pointsHistoryRepository.save(pointsHistoryEntity);
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
